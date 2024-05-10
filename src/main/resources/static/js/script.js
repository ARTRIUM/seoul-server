const indicator = document.querySelector('.nav-indicator');
const items = document.querySelectorAll('.gu-menu-item');

function handleIndicator(el) {
  items.forEach(item => {
    item.classList.remove('menu-active');
    item.removeAttribute('style');
  });

  indicator.style.width = `${el.offsetWidth}px`;
  indicator.style.left = `${el.offsetLeft}px`;
  indicator.style.backgroundColor = el.getAttribute('active-color');

  el.classList.add('menu-active');
  el.style.color = el.getAttribute('active-color');
}


items.forEach((item, index) => {
  item.addEventListener('click', e => {handleIndicator(e.target);});
  item.classList.contains('menu-active') && handleIndicator(item);
});