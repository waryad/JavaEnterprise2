/*function getCalendar(id) {
	if (document.getElementById("calendar" + id).innerHTML == "") {
		//document.getElementById("calendar" + id).innerHTML = "hello Deepinder!";

		fetch('http://localhost:8080/getCalendar/' + id) // use HomeController to fetch from our service

			.then(calendar => calendar.json()) // JSONify the data returned
			.then(function(calendar) {      // with the JSON data

				// modify textToDisplay below here!
				var textToDisplay = "";             // create and append to a blank var
				// textToDisplay += "ID: " + calendar.id + "<br>";
				textToDisplay += "title: " + calendar.title + "<br>";
				textToDisplay += "noworkDay: " + calendar.noworkDay + "<br>";
				textToDisplay += "dolistHoliday: " + calendar.dolistHoliday + "<br>";
				textToDisplay += "hdayDate: " + calendar.hdayDate + "<br>";
				textToDisplay += "hdayTime: " + calendar.hdayTime + "<br>";
				textToDisplay += "message: " + calendar.message + "<br>";

				// finally, change our relevant div to display the var
				document.getElementById("calendar" + id).innerHTML = textToDisplay;
			});

	} else {
		document.getElementById("calendar" + id).innerHTML = "";
	}
}*/



function getCalendar(id) {
    if (document.getElementById("calendar" + id).innerHTML == "") {
        fetch('http://localhost:8080/calendars/' + id) // Use the correct URL
            .then(response => {
                if (!response.ok) {
                    throw new Error("HTTP error, status = " + response.status);
                }
                return response.json();
            })
            .then(function(calendar) {
                // Modify textToDisplay here
                var textToDisplay = "";
                textToDisplay += "title: " + calendar.title + "<br>";
                textToDisplay += "noworkDay: " + calendar.noworkDay + "<br>";
                textToDisplay += "dolistHoliday: " + calendar.dolistHoliday + "<br>";
                textToDisplay += "hdayDate: " + calendar.hdayDate + "<br>";
                textToDisplay += "hdayTime: " + calendar.hdayTime + "<br>";
                textToDisplay += "message: " + calendar.message + "<br>";

                document.getElementById("calendar" + id).innerHTML = textToDisplay;
            })
            .catch(error => {
                console.error('Error:', error);
            });
    } else {
        document.getElementById("calendar" + id).innerHTML = "";
    }
}

