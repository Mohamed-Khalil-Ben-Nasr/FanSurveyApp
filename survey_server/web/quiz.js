var user, quiz, questions;

// Work-around for missing JQuery.postJSON
jQuery["postJSON"] = function(url,data,callback) {
	$.ajax({
	  url:url,
	  type:'POST',
	  data:JSON.stringify(data),
	  contentType:'application/json',
	  dataType:'json',
	  success: callback
	});
};

function buildStudentsList(data) {
	var n, length, studentsSelect;
	studentsSelect = $('#names');
	length = data.length;
	for(n = 0;n < length;n++) {
		studentsSelect.append($('<option>', {
    value: data[n].id,
    text: data[n].name
		}));
	}
}

function buildQuizList(data) {
	var n, length, quizSelect;
	quizSelect = $('#quiz');
	length = data.length;
	for(n = 0;n < length;n++) {
		quizSelect.append($('<option>', {
    value: data[n].id,
    text: data[n].title
		}));
	}
}

function loadQuestions() {
	$('#one').hide();
	$('#two').show();

	quiz = $('#quiz option:selected').val();
	user = $('#names option:selected').val();
	$.getJSON('http://localhost:8085/questions?quiz='+quiz,buildQuestions);
}

// Callback function for the GET query that fetches the questions.
// This function will build the questions into the page
function buildQuestions(data) {
	questions = data; // Save the questions in a global variable

	var n, length, parentDiv, questionDiv, choicesDiv, options, m, split, newChoice, letters;
	length = questions.length;
	parentDiv = $('#questions');
	for(n = 0;n < length;n++) {
		// Each of the questions appears in its own div. The div contains a paragraph for the text
		// of the question and a div below that that displays radio buttons for the responses

		// When setting up the radio buttons we give each element a name attribute
		// that contains the index in the questions array of the question that this element will
		// answer.
		questionDiv = $('<div>');
		questionDiv.append($('<p>').text(questions[n].question).addClass('text-info'));
		choicesDiv = $('<div>').addClass('form-check form-check-inline');
		options = questions[n].choices;
		split = options.split(',');
		letters = ['a','b','c','d','e','f','g','h'];
		for(m = 0;m < split.length;m++) {
			newChoice = $('<label>').addClass('form-check-inline');
			// For each option we set up a radio button. The value of the radio button element is
			// the option.
			newChoice.html('<input class="form-check-input" type="radio" name="radio_'+n+'" value="'+letters[m]+'">'+split[m]);
			choicesDiv.append(newChoice);
			}

		questionDiv.append(choicesDiv);
		parentDiv.append(questionDiv);
	}
}

// Event handler for the submit button at the bottom of the page
function submitAnswers() {
	var n, length, response, choice, value;
	length = questions.length;
	// For each of the questions stored in the global questions array we need
	// to locate the user's response in the page and upload their response.
	for(n = 0;n < length;n++) {
		// The key to locating the user's responses are the name attributes we used
		// when setting up the questions in the page. These name attributes take the
		// form of radio_<n>, where <n> is the index in the questions
		// array for the question that this element is linked to.
		// We can locate the elements in question by using JQuery selectors of the form
		// input[name="radio_<n>"]:checked
		choice = $("input[name='radio_"+n+"']:checked").val();
		// Assemble the response object for this question and post it to the server.
		response = {student:user,question:questions[n].id,response:choice};
		$.postJSON('http://localhost:8085/responses',response);
	}
	// Hide the section that displays the questions and show the confirmation section.
	$('#two').hide();
	$('#three').show();
}

// Event handler for the document ready event
function setUp() {
	$('#take').on('click',loadQuestions);
	$('#submit').on('click',submitAnswers);

	$('#two').hide();
	$('#three').hide();

	// Set up the two AJAX GET requests that will fetch the lists of names and quizzes.
	$.getJSON('http://localhost:8085/students',buildStudentsList);
	$.getJSON('http://localhost:8085/quizzes',buildQuizList);
}

$(document).ready(setUp);
