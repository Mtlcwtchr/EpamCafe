$.fn.popup = function() { 	//функция для открытия всплывающего окна:
    this.css('position', 'fixed').fadeIn();
    this.css('top', 20 + '%');
    this.css('left', 40  + '%');
}

$(document).ready(function(){	//при загрузке страницы:

    $('.popup-window').fadeOut();
    $('.background-shadow').fadeOut();

    document.querySelectorAll('.popup-open').forEach((link)=>{link.addEventListener('click',
                ()=>{$('.p-w-'+link.getAttribute('about')).popup(); $('.background-shadow').fadeIn();})})

    document.querySelectorAll('.close', ).forEach((link)=>{link.addEventListener('click',
                ()=>{ $('.popup-window').fadeOut(); $('.background-shadow').fadeOut(); })})

    document.querySelectorAll('.background-shadow').forEach((link)=>{link.addEventListener('click',
                ()=>{ $('.popup-window').fadeOut(); $('.background-shadow').fadeOut(); })})

    if (document.location.search.substring(document.location.search.lastIndexOf('?'), document.location.search.lastIndexOf('='))==='?open') {
        $('.p-w-'+document.location.search.substring(document.location.search.lastIndexOf('=')+1, document.location.search.length)).popup();
        $('.background-shadow').fadeIn();
    }

    if (document.location.search.includes('?success=true') || document.location.search.includes('&success=true')) {
            $('.p-w-p').popup();
            $('.background-shadow').popup();
    }

});