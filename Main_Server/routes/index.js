var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.get('/cot/_api/message', function(req, res) {
    console.log("in message");
    var message = translateMessage(req.message);
    var to = req.to;
    res.json( { message : "Ok, will turn on every day in 8:00AM" });
});

function translateMessage(message) {
    return message;
}


module.exports = router;
