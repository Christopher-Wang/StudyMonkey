const showMetrics = {
    setup: function () {
        $(document).on('click', '#showPieChartBtn', function (e) {
            e.preventDefault();
            let id = document.getElementById("mcQuestionId").innerText;
            showMetrics.getPieChart(id);
        });
    }
    , getPieChart: function (questionId) {
        $.ajax({
            type: 'GET',
            url: `/answer/questionAnswersJSON/${questionId}`,
            timeout: 5000,
            success: function (data, requestStatus, xhrObject) {
                if (xhrObject.status === 200) {
                    $("#showPieChart").empty().append(`<div style="position: relative;display:block;margin: auto;width: 40vw;"><canvas id="myChart"></canvas></div>`);
                    if(data.length != 0) {
                        analyseData(data);
                    } else{
                        $("#showPieChart").empty().append("<div style=\"text-align:center;\"><h2 class=\"m-3\">No Answers Available</h2></div>");
                    }
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
    let allLabel = data.map(item => item.mcOption.option);

    let occurrences = allLabel.reduce(function (option, counter) {
        if (typeof option[counter] == 'undefined') {
            option[counter] = 1;
        } else {
            option[counter] += 1;
        }
        return option;
    }, {});

    let labels = Object.keys(occurrences);
    let frequencyOfAnsweredLabels = Object.values(occurrences);
    setupPieChart(labels,frequencyOfAnsweredLabels)
}


function setupPieChart(labels, barchartData) {
    let backgroundColor = ['rgba(255, 99, 132, 0.2)',
        'rgba(255, 159, 64, 0.2)',
        'rgba(255, 205, 86, 0.2)',
        'rgba(75, 192, 192, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(153, 102, 255, 0.2)',
        'rgba(201, 203, 207, 0.2)']

    let borderColor = ['rgb(255, 99, 132)',
        'rgb(255, 159, 64)',
        'rgb(255, 205, 86)',
        'rgb(75, 192, 192)',
        'rgb(54, 162, 235)',
        'rgb(153, 102, 255)',
        'rgb(201, 203, 207)'];

    let data = {
        datasets: [{
            fill: true,
            backgroundColor: backgroundColor,
            borderColor: borderColor,
            data: barchartData
        }],

        // These labels appear in the legend and in the tooltips when hovering different arcs
        labels: labels
    };

    const ctx = document.getElementById('myChart');
    const myPieChart = new Chart(ctx, {
        type: 'pie',
        data: data,
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'bottom',
                    align: 'vertical'
                },
                title: {
                    display: true,
                    text: 'Distribution of Answered Options for the Question'
                }
            }
        },
    });
}


$(document).ready(function () {
    showMetrics.setup();
})