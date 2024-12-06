const apiBaseUrl = "http://localhost:8080";

async function startGame() {
    try {
        document.getElementById("startButton").disabled = true;
        document.getElementById("a1").disabled = true;
        document.getElementById("scenario_2").disabled = true;
        document.getElementById("scenario_3").disabled = true;
        document.getElementById("scenario_4").disabled = true;

        const response = await fetch(`${apiBaseUrl}/start`, {method: "POST"});
        const result = await response.json();
        console.log("Start Game Response:", result);

        document.getElementById("textbox").innerText += "\nGame Start!\n" + result.text;
        document.getElementById("textbox").scroll(0, 1000)

        document.getElementById("p1hand").innerText = result.player1Hand
        document.getElementById("p1shields").innerText = result.player1Shield
        document.getElementById("p2hand").innerText = result.player2Hand
        document.getElementById("p2shields").innerText = result.player2Shield
        document.getElementById("p3hand").innerText = result.player3Hand
        document.getElementById("p3shields").innerText = result.player3Shield
        document.getElementById("p4hand").innerText = result.player4Hand
        document.getElementById("p4shields").innerText = result.player4Shield
    } catch (error) {
        console.error("Error in startGame:", error);
    }
}

async function a1() {
    try {
        document.getElementById("startButton").disabled = true;
        document.getElementById("a1").disabled = true;
        document.getElementById("scenario_2").disabled = true;
        document.getElementById("scenario_3").disabled = true;
        document.getElementById("scenario_4").disabled = true;

        const response = await fetch(`${apiBaseUrl}/a1`, {method: "POST"});
        const result = await response.json();
        console.log("Start Game a1 Response:", result);

        document.getElementById("textbox").innerText += "\nGame Start!\n" + result.text;
        document.getElementById("textbox").scroll(0, document.getElementById("textbox").scrollHeight);

        document.getElementById("p1hand").innerText = result.player1Hand
        document.getElementById("p1shields").innerText = result.player1Shield
        document.getElementById("p2hand").innerText = result.player2Hand
        document.getElementById("p2shields").innerText = result.player2Shield
        document.getElementById("p3hand").innerText = result.player3Hand
        document.getElementById("p3shields").innerText = result.player3Shield
        document.getElementById("p4hand").innerText = result.player4Hand
        document.getElementById("p4shields").innerText = result.player4Shield
    } catch (error) {
        console.error("Error in startGame:", error);
    }
}

async function scenario_2() {
    try {
        document.getElementById("startButton").disabled = true;
        document.getElementById("a1").disabled = true;
        document.getElementById("scenario_2").disabled = true;
        document.getElementById("scenario_3").disabled = true;
        document.getElementById("scenario_4").disabled = true;

        const response = await fetch(`${apiBaseUrl}/2w2g`, {method: "POST"});
        const result = await response.json();
        console.log("Start Game 2w2g Response:", result);

        document.getElementById("textbox").innerText += "\nGame Start!\n" + result.text;
        document.getElementById("textbox").scroll(0, document.getElementById("textbox").scrollHeight);

        document.getElementById("p1hand").innerText = result.player1Hand
        document.getElementById("p1shields").innerText = result.player1Shield
        document.getElementById("p2hand").innerText = result.player2Hand
        document.getElementById("p2shields").innerText = result.player2Shield
        document.getElementById("p3hand").innerText = result.player3Hand
        document.getElementById("p3shields").innerText = result.player3Shield
        document.getElementById("p4hand").innerText = result.player4Hand
        document.getElementById("p4shields").innerText = result.player4Shield
    } catch (error) {
        console.error("Error in startGame:", error);
    }
}

async function scenario_3() {
    try {
        document.getElementById("startButton").disabled = true;
        document.getElementById("a1").disabled = true;
        document.getElementById("scenario_2").disabled = true;
        document.getElementById("scenario_3").disabled = true;
        document.getElementById("scenario_4").disabled = true;

        const response = await fetch(`${apiBaseUrl}/1we`, {method: "POST"});
        const result = await response.json();
        console.log("Start Game 1we Response:", result);

        document.getElementById("textbox").innerText += "\nGame Start!\n" + result.text;
        document.getElementById("textbox").scroll(0, document.getElementById("textbox").scrollHeight);

        document.getElementById("p1hand").innerText = result.player1Hand
        document.getElementById("p1shields").innerText = result.player1Shield
        document.getElementById("p2hand").innerText = result.player2Hand
        document.getElementById("p2shields").innerText = result.player2Shield
        document.getElementById("p3hand").innerText = result.player3Hand
        document.getElementById("p3shields").innerText = result.player3Shield
        document.getElementById("p4hand").innerText = result.player4Hand
        document.getElementById("p4shields").innerText = result.player4Shield
    } catch (error) {
        console.error("Error in startGame:", error);
    }
}

async function scenario_4() {
    try {
        document.getElementById("startButton").disabled = true;
        document.getElementById("a1").disabled = true;
        document.getElementById("scenario_2").disabled = true;
        document.getElementById("scenario_3").disabled = true;
        document.getElementById("scenario_4").disabled = true;

        const response = await fetch(`${apiBaseUrl}/0wq`, {method: "POST"});
        const result = await response.json();
        console.log("Start Game 0wq Response:", result);

        document.getElementById("textbox").innerText += "\nGame Start!\n" + result.text;
        document.getElementById("textbox").scroll(0, document.getElementById("textbox").scrollHeight);

        document.getElementById("p1hand").innerText = result.player1Hand
        document.getElementById("p1shields").innerText = result.player1Shield
        document.getElementById("p2hand").innerText = result.player2Hand
        document.getElementById("p2shields").innerText = result.player2Shield
        document.getElementById("p3hand").innerText = result.player3Hand
        document.getElementById("p3shields").innerText = result.player3Shield
        document.getElementById("p4hand").innerText = result.player4Hand
        document.getElementById("p4shields").innerText = result.player4Shield
    } catch (error) {
        console.error("Error in startGame:", error);
    }
}

async function submit() {
    try {

        document.getElementById("submit").disabled = true;
        document.getElementById("in").disabled = true;
        var val= document.getElementById("in").value;
        var response = ""
        if (val == ""){
            console.log("empty");
            response = await fetch(`${apiBaseUrl}/empty`, {method: "POST"});
        }else {
            document.getElementById("textbox").innerText += val + "\n";
            document.getElementById("in").value = "";
            response = await fetch(`${apiBaseUrl}/output/${val}`, {method: "POST"});
        }
        const result = await response.json();
        console.log("Start Game Response:", result);

        document.getElementById("textbox").innerText += result.text;
        document.getElementById("textbox").scroll(0, document.getElementById("textbox").scrollHeight)

        document.getElementById("p1hand").innerText = result.player1Hand
        document.getElementById("p1shields").innerText = result.player1Shield
        document.getElementById("p2hand").innerText = result.player2Hand
        document.getElementById("p2shields").innerText = result.player2Shield
        document.getElementById("p3hand").innerText = result.player3Hand
        document.getElementById("p3shields").innerText = result.player3Shield
        document.getElementById("p4hand").innerText = result.player4Hand
        document.getElementById("p4shields").innerText = result.player4Shield

        if(result.gameFinished == true){
            document.getElementById("gameState").innerText = "Game is over!";
        }else {
            document.getElementById("submit").disabled = false;
            document.getElementById("in").disabled = false;
        }
    } catch (error) {
        console.error("Error in communication:", error);
        document.getElementById("submit").disabled = false;
        document.getElementById("in").disabled = false;
    }
}