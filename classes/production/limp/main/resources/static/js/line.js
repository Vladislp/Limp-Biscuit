var currentPage = 5; // prefilled by server
function go(d) {
    setupPage(currentPage + d);
    history.pushState(currentPage, document.title, '?x=' + currentPage);
}

onpopstate = function (event) {
    setupPage(event.state);
}

function setupPage(page) {
    currentPage = page;
    document.title = 'Line Game - ' + currentPage;
    document.getElementById('coord').textContent = currentPage;
    document.links[0].href = '?x=' + (currentPage + 1);
    document.links[0].textContent = 'Advance to ' + (currentPage + 1);
    document.links[1].href = '?x=' + (currentPage - 1);
    document.links[1].textContent = 'retreat to ' + (currentPage - 1);
}