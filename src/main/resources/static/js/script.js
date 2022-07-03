const toggle = document.querySelector('.toggle')
const navigation = document.querySelector('.navigation')

toggle.addEventListener('click', () => {
	toggle.classList.toggle('active')
	navigation.classList.toggle('active')
});

const msg_wrapper = document.querySelector('.msg__wrapper');
const click = document.querySelector('.clickMe');

	click.addEventListener("click", () => {
		msg_wrapper.classList.add('inactive')
	});
	msg_wrapper.addEventListener("click", () => {
		msg_wrapper.classList.add('inactive')
	});
	
	
