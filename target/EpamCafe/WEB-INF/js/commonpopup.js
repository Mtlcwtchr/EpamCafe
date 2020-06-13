$.fn.popup = function() { 	//функция для открытия всплывающего окна:
    this.css('position', 'absolute').fadeIn();
    this.css('top', (this.height() * 0.25) + 'px');
    this.css('left', (($(window).width() - this.width()) / 2)  + 'px');
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