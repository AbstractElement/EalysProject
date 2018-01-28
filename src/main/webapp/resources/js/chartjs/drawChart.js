$(document).ready(function () {
    var projectId = $('#riskChart').attr('data-id');
    getChart(projectId);

});

function drawChart(pointArr) {
    var ctx = document.getElementById("riskChart");
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"],
            datasets: [{
                label: 'Number of risks',
                data: pointArr,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(230, 99, 132, 0.2)',
                    'rgba(60, 162, 235, 0.2)',
                    'rgba(200, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(230, 99, 132, 1)',
                    'rgba(60, 162, 235, 1)',
                    'rgba(200, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: true,
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero:true,
                        stepSize:5
                    }
                }]
            }
        }
    });
}

