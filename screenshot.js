const puppeteer = require('puppeteer');

(async () => {
    const browser = await puppeteer.launch();
    const page = await browser.newPage();
    await page.setViewport({ width: 1200, height: 800 });

    console.log('Taking screenshot of home page...');
    await page.goto('http://localhost:8080/');
    await page.screenshot({ path: 'home-page.png' });

    console.log('Taking screenshot of list books page...');
    await page.goto('http://localhost:8080/books');
    await page.screenshot({ path: 'list-books.png' });

    console.log('Taking screenshot of add book page...');
    await page.goto('http://localhost:8080/books/new');
    await page.screenshot({ path: 'add-book.png' });

    console.log('Taking screenshot of edit book page...');
    await page.goto('http://localhost:8080/books/edit/1');
    await page.screenshot({ path: 'edit-book.png' });

    await browser.close();
    console.log('Done!');
})();
