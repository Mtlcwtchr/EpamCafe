$.fn.popup = function() { 	//функция для открытия всплывающего окна:
    this.css('position', 'absolute').fadeIn();	//задаем абсолютное позиционирование и эффект открытия
    //махинации с положением сверху:учитывается высота самого блока, экрана и позиции на странице:
    this.css('top', $(window).scrollTop()/2 + 'px');
    //слева задается отступ, учитывается ширина самого блока и половина ширины экрана
    this.css('left', ($(window).width() - this.width()) / 2  + 'px');
}

$(document).ready(function(){	//при загрузке страницы:

    $('.popup-window').fadeOut();

    document.querySelectorAll('.popup-open').forEach((link)=>{link.addEventListener('click',
        ()=>{ $('.p-w-'+link.getAttribute('about')).popup()})})

    document.querySelectorAll('.close').forEach((link)=>{link.addEventListener('click',
                ()=>{ $('.popup-window').fadeOut();})})

    if (document.location.search.substring(document.location.search.lastIndexOf('?'), document.location.search.lastIndexOf('='))==='?open') {
        $('.p-w-'+document.location.search.substring(document.location.search.lastIndexOf('=')+1, document.location.search.length)).popup();
    }

    if (document.location.search.substring(document.location.search.lastIndexOf('?'), document.location.search.lastIndexOf('='))==='?success' ||
        document.location.search.substring(document.location.search.lastIndexOf('&'), document.location.search.lastIndexOf('='))==='&success') {
        if (document.location.search.substring(document.location.search.lastIndexOf('=') + 1, document.location.search.length)) {
            $('.p-w-p').popup();
        }
    }

});