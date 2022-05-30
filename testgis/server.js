const express = require('express');
const app = express();

app.get('/', (req, res) => {
    res.send('Hello world!');
    console.log('get req');
});

app.listen(6955, () => {
    console.log('server listening on port 6955');
})