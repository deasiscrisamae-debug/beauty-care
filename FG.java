const player1 = document.getElementById("player1");
const player2 = document.getElementById("player2");
const message = document.getElementById("message");

let p1Health = 3;
let p2Health = 3;

document.addEventListener("keydown", (event) => {
    switch(event.key) {
        // Player 1 Controls (A, D, Space)
        case "a":
            move(player1, -20);
            break;
        case "d":
            move(player1, 20);
            break;
        case " ":
            attack(player1, player2);
            break;
        
        // Player 2 Controls (Left, Right, Enter)
        case "ArrowLeft":
            move(player2, -20);
            break;
        case "ArrowRight":
            move(player2, 20);
            break;
        case "Enter":
            attack(player2, player1);
            break;
    }
});

// Move function
function move(player, distance) {
    let currentPos = parseInt(window.getComputedStyle(player).getPropertyValue("left")) || 0;
    let newPos = currentPos + distance;
    if (newPos >= 0 && newPos <= 550) { // Prevent going out of bounds
        player.style.left = newPos + "px";
    }
}

// Attack function
function attack(attacker, defender) {
    attacker.classList.add("punch");
    setTimeout(() => attacker.classList.remove("punch"), 300);

    let attackerPos = parseInt(window.getComputedStyle(attacker).getPropertyValue("left")) || 0;
    let defenderPos = parseInt(window.getComputedStyle(defender).getPropertyValue("left")) || 0;

    if (Math.abs(attackerPos - defenderPos) < 60) {
        if (defender === player1) {
            p1Health--;
        } else {
            p2Health--;
        }
        checkGameOver();
    }
}

// Check for game over
function checkGameOver() {
    if (p1Health === 0) {
        message.innerText = "Player 2 Wins!";
    } else if (p2Health === 0) {
        message.innerText = "Player 1 Wins!";
    }
}

