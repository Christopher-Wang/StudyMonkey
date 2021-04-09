const showMetrics = {
    setup: function () {
        $(document).on('click', '#showRangeBarchartBtn', function (e) {
            e.preventDefault();
            let id = document.getElementById("rangeQuestionId").innerText;
            showMetrics.getBarchart(id);
        });
    }
    , getBarchart: function (questionId) {
        $.ajax({
            type: 'GET',
            url: `/answer/questionAnswersJSON/${questionId}`,
            timeout: 5000,
            success: function (data, requestStatus, xhrObject) {
                if (xhrObject.status === 200) {
                    $("#showRangeBarchart").empty().append(`<div style="position: relative;margin: auto;width: 90vw;"><canvas id="myChart"></canvas></div>`);
                    analyseData(data);
                } else {
                    alert("Something went wrong.");
                }
            },
            error: function (xhrObj, textStatus, exception) {
                alert('Error!');
            }
        });
        return false;  // prevent default link action
    }
};

function analyseData(data) {
    let allNum = data.map(item => item.numAnswer);  // extract to array
    let labels = new Set(allNum) // create set
    labels = Array.from(labels);
    let barchartData = Array.from({length: labels.length}, () => 0)    // create empty array
    console.log("length", barchartData.length)
    // map to the array
    let i;
    for (i = 0; i < allNum.length; i++) {
        let index = labels.indexOf(allNum[i]);
        barchartData.splice(index, 1, barchartData[index] + 1); // add to index in array
    }
    setupBarChart(labels, barchartData)
}

function setupBarChart(labels, barchartData) {
    const ctx = document.getElementById('myChart');
    const data = {
        labels: labels,
        datasets: [{
            label: 'No. of answers',
            data: barchartData,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(255, 205, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(201, 203, 207, 0.2)'
            ],
            borderColor: [
                'rgb(255, 99, 132)',
                'rgb(255, 159, 64)',
                'rgb(255, 205, 86)',
                'rgb(75, 192, 192)',
                'rgb(54, 162, 235)',
                'rgb(153, 102, 255)',
                'rgb(201, 203, 207)'
            ],
            borderWidth: 1
        }]
    };
    const options = {
        plugins: {
            title: {
                display: true,
                text: document.getElementById("questionTitle").innerText,
                font: {
                    family: 'Comic Sans MS',
                    size: 20,
                    style: 'normal',
                    lineHeight: 1.2,
                },
            }
        },
        responsive: true,
        scales: {
            x: {
                display: true,
                title: {
                    display: true,
                    text: 'Range',
                    font: {
                        family: 'Comic Sans MS',
                        size: 20,
                        style: 'bold',
                        lineHeight: 1.2,
                    },
                    padding: {top: 20, left: 0, right: 0, bottom: 0}
                }
            },
            y: {
                display: true,
                title: {
                    display: true,
                    text: 'No. of answers',
                    font: {
                        family: 'Comic Sans MS',
                        size: 20,
                        style: 'normal',
                        lineHeight: 1.2,
                    },
                    padding: {top: 30, left: 0, right: 0, bottom: 0}
                }
            }
        }
    };

    const myBarChart = new Chart(ctx, {
        type: 'bar',
        data: data,
        options: options
    });
}

$(document).ready(function () {
    showMetrics.setup();
})