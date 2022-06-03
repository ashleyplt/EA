const prevBtns = document.querySelectorAll(".btn-prev");
const progress = document.getElementById("progress");
const formSteps = document.querySelectorAll(".form-step");
const progressSteps = document.querySelectorAll(".progress-step");

let formStepsNum = 0;

function n1() {


	var texto = $("#periodo").val();
	var texto2 = $("#nombre").val();
	var texto3 = $("#asignatura").val();
	var texto4 = $("#seccion").val();
	if (texto == "") {
		input = "Periodo";
		salert(input)
		return false;
	}
	if (texto2 == "") {
		input = "Nombre"
		salert(input)
		return false;
	}
	if (texto3 == "") {
		input = "Asignatura";
		salert(input)
		return false;
	}
	if (texto4 == "") {
		input = "Sección"
		salert(input)
		return false;
	}

	if (texto4.length < 2) {
		input = "La sección debe contener al menos 2 caracteres"
		salert2(input)
		return false;
	}
	formStepsNum++;
	updateFormSteps();
	updateProgressbar();

}

function n2() {


	var numero = document.forms['form']['total'];
	var numero2 = document.forms['form']['totalr'];
	var numero3 = document.forms['form']['totala'];
	var numero4 = document.forms['form']['sdc'];
	var input = "";



	var n = parseInt($("#totala").val());

	var n2 = parseInt($("#totalr").val());
	var suma = n + n2;


	if (numero.value == '') {
		input = "Total de estudiantes Matriculados con un numero valido";
		salert(input)
		return false;
	}




	if (numero.value < 1) {
		input = "Total de estudiantes Matriculados con un numero valido";
		salert(input)
		return false;
	}
	if (numero2.value == '') {
		input = "Total de estudiantes Reprobados";
		salert(input)
		return false;
	}
	if (numero2.value <= -1) {
		input = "Total de estudiantes Reprobados con un numero valido";
		salert(input)
		return false;
	}
	if (numero3.value == '') {
		input = "Total de estudiantes Aprobados";
		salert(input)
		return false;
	}
	if (numero3.value <= -1) {
		input = "Total de estudiantes Aprobados con un numero valido";
		salert(input)
		return false;
	}
	if (suma != numero.value) {
		input = "La cantidad de Alumnos aprobados y reprobados no coincide con el total de alumnos";
		salert2(input)
		return false;
	}
	if (numero4.value == '') {
		input = "Total de estudiantes SDC";
		salert(input)
		return false;
	}
	if (numero4.value <= -1) {
		input = "Total de estudiantes SDC con un numero valido";
		salert(input)
		return false;
	}






	formStepsNum++;
	updateFormSteps();
	updateProgressbar();

}



function n3() {



	var texto5 = $("#valoracion").val();
	var texto6 = $("#valoracionC").val();
	var texto7 = $("#valoracionCon").val();

	var input = "";
	if (texto5 == "") {
		input = "Valoración de la Participación";
		salert(input)
		return false;
	}
	if (texto5.length < 20) {
		input = "La Valoración de la Participación debe contener al menos 20 caracteres"
		salert2(input)
		return false;
	}
	if (texto6 == "") {
		input = "Valoración del Cumplimiento"
		salert(input)
		return false;
	}
	if (texto6.length < 20) {
		input = "La Valoración del cumplimiento debe contener al menos 20 caracteres"
		salert2(input)
		return false;
	}
	if (texto7 == "") {
		input = "Valoración del Contenido";
		salert(input)
		return false;
	}
	
	
	if (texto7.length < 12) {
		input = "La Valoración del contenido debe contener al menos 12 caracteres"
		salert2(input)
		return false;
	}

	formStepsNum++;
	updateFormSteps();
	updateProgressbar();

}

function n4() {

var enviar = true;

	var texto8 = $("#valoracionMet").val();
	var texto9 = $("#valoracionT").val();
	var texto10 = $("#valoracionP").val();
	var input = "";


	if (texto8 == "") {
		input = "Valoración de la Eficiencia"
		salert(input)
		enviar = false;
		return false;
	}
	
	if (texto8.length < 20) {
		input = "La Valoración de la eficiencia debe contener al menos 20 caracteres"
		salert2(input)
		return false;
	}
	if (texto9 == "") {
		input = "Valoración de los tipos de evaluación";
		salert(input)
		enviar = false;
		return false;
	}
	
	if (texto9.length < 20) {
		input = "La Valoración de los tipos de evaluaciones  debe contener al menos 20 caracteres"
		salert2(input)
		return false;
	}
	if (texto10 == "") {
		input = "Valoración de la Pertinencia"
		salert(input)
		enviar = false;
		return false;
	}
	if (texto10.length < 20) {
		input = "La Valoración de la pertinencia debe contener al menos 20 caracteres"
		salert2(input)
		return false;
	}

if( enviar == true){
		
		
	document.form.action = "../SLValoracion";
	document.form.submit();
	
	}

}
prevBtns.forEach((btn) => {
	btn.addEventListener("click", () => {
		formStepsNum--;
		updateFormSteps();
		updateProgressbar();
	});
});

function updateFormSteps() {
	formSteps.forEach((formStep) => {
		formStep.classList.contains("form-step-active") &&
			formStep.classList.remove("form-step-active");
	});

	formSteps[formStepsNum].classList.add("form-step-active");
}

function updateProgressbar() {
	progressSteps.forEach((progressStep, idx) => {
		if (idx < formStepsNum + 1) {
			progressStep.classList.add("progress-step-active");
		} else {
			progressStep.classList.remove("progress-step-active");
		}
	});

	const progressActive = document.querySelectorAll(".progress-step-active");

	progress.style.width =
		((progressActive.length - 1) / (progressSteps.length - 1)) * 100 + "%";
}