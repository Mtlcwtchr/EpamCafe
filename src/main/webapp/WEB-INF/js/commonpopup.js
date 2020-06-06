$.fn.popup = function() { 	//функция для открытия всплывающего окна:
    this.css('position', 'absolute').fadeIn();	//задаем абсолютное позиционирование и эффект открытия
    //махинации с положением сверху:учитывается высота самого блока, экрана и позиции на странице:
    this.css('top', ($(window).height() - this.height()) / 2 + $(window).scrollTop() + 'px');
    //слева задается отступ, учитывается ширина самого блока и половина ширины экрана
    this.css('left', ($(window).width() - this.width()) / 2  + 'px');
    //открываем тень с эффектом:
    $('.backpopup').fadeIn();
}
$.fn.popout = function() {
    this.css().fadeOut();
}

$(document).ready(function(){	//при загрузке страницы:

    $('.popup-window').popout();
    $('.backpopup').popout();

    document.querySelectorAll('.popup-open').forEach((link)=>{link.addEventListener('click',
        ()=>{ $('.p-w-'+link.getAttribute('about')).popup()})})

    document.querySelector('.close').addEventListener((link)=>{link.addEventListener('click',
        ()=>{
        $('.popup-window').popout();
        $('.backpopup').popout();})})

    document.querySelector('.backpopup').addEventListener((link)=>{link.addEventListener('click',
        ()=>{
        $('.popup-window').popout();
        $('.backpopup').popout();})})

    if (document.location.search.substring(document.location.search.lastIndexOf('?'), document.location.search.lastIndexOf('='))==='?open') {
        $('.p-w-'+document.location.search.substring(document.location.search.lastIndexOf('=')+1, document.location.search.length)).popup();
    }

});