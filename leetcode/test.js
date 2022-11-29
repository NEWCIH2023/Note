removeBlock(document.querySelectorAll('.recommend-box'))
removeBlock([document.querySelector('.blog_container_aside'), document.querySelector('.recommend-right'), document.querySelector('.blog-footer-bottom'),
document.querySelector('#recommendNps'), document.querySelector('#toolBarBox'), document.querySelector('.template-box'),
document.querySelector('#blogColumnPayAdvert'), document.querySelector('#csdn-toolbar'), document.querySelector('.article-info-box'),
document.querySelector('#pcCommentBox')])

document.querySelector('.main_father').style.padding = '0';
document.querySelector('main div.blog-content-box').style.padding = '0';
document.querySelector('.nodata .container, .nodata .recommend-right').style.marginLeft = '0';
document.querySelector('#mainBox').style.width = '100%';
document.querySelector('main').style.width = '100%';
document.querySelector('main div.blog-content-box pre.set-code-hide .hide-preCode-box .look-more-preCode').click();

function removeBlock(elements) {
    if (elements && elements.length > 0) {
        elements.forEach(element => {
            element && element.remove();
        })
    }
}