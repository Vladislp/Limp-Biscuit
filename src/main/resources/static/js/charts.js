// http://nikolay.rocks/2015-10-29-rainbows-generator-in-javascript
function sin_to_hex(i, phase, size) {
    var sin = Math.sin(Math.PI / size * 2 * i + phase);
    var int = 128 + Math.floor((Math.floor(sin * 127) + 128) / 2);
    var hex = int.toString(16);

    return hex.length === 1 ? "0" + hex : hex;
}

function drawBarChart(id, histogram) {
    var canvas = document.getElementById(id);
    var context = canvas.getContext("2d");
    var stats = canvas.getAttribute("data-stats");

    var labels = [];
    var data = [];

    var pairs = stats === null ? [] : stats.split(";");

    for (var i = 0; i < pairs.length; ++i) {
        var splits = pairs[i].split("/");

        if (histogram) {
            data.push({x: splits[0], y: parseInt(splits[1])})
        } else {
            labels.push(splits[0]);
            data.push(parseInt(splits[1]));
        }
    }

    // http://nikolay.rocks/2015-10-29-rainbows-generator-in-javascript
    var hoverBackgroundColors = new Array(data.length);
    var backgroundColors = new Array(data.length);
    var borderColors = new Array(data.length);

    for (var i = 0; i < data.length; i++) {
        var red = sin_to_hex(i, 0, data.length);
        var blue = sin_to_hex(i, Math.PI * 2 / 3, data.length);
        var green = sin_to_hex(i, 2 * Math.PI * 2 / 3, data.length);

        hoverBackgroundColors[i] = "#" + red + green + blue + "26";
        backgroundColors[i] = "#" + red + green + blue + "22";
        borderColors[i] = "#" + red + green + blue;
    }

    console.log(backgroundColors);

    if (data.length > 0) {
        new Chart(context, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    data: data,
                    hoverBackgroundColor: hoverBackgroundColors,
                    backgroundColor: backgroundColors,
                    borderColor: borderColors,
                    borderWidth: 1
                }]
            },
            options: {
                legend: {
                    display: false,
                    labels: {
                        fontColor: "rgba(255, 255, 255, 0.5)"
                    }
                },
                scales: {
                    yAxes: [{
                        gridLines: {
                            display: false,
                            color: "rgba(255, 255, 255, 0.5)"
                        },
                        ticks: {
                            fontColor: "rgba(255, 255, 255, 0.5)",
                            beginAtZero: true
                        }
                    }],
                    xAxes: histogram ? [{
                        gridLines: {
                            display: false,
                            color: "rgba(255, 255, 255, 0.5)"
                        },
                        type: 'time',
                        ticks: {
                            fontColor: "white"
                        },
                        time: {
                            unit: "minute"
                        },
                        offset: true
                    }] : [{
                        gridLines: {
                            display: false,
                            color: "rgba(255, 255, 255, 0.5)"
                        },
                        ticks: {
                            fontColor: "white"
                        }
                    }]
                }
            }
        });
    }
}

drawBarChart("operating-systems", false);
drawBarChart("browsers", false);
drawBarChart("dates", true);