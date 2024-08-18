function setModeCookie(mode) {
    document.cookie = "theme=" + mode + "; path=/; max-age=" + 60*60*24*365 + "; SameSite=Lax"; 
}

function getModeCookie() {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; theme=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
    return null;
}

function applyMode(mode) {
    if (mode === "dark") {
        document.body.classList.add("dark-mode");
        document.body.classList.remove("light-mode");
        document.getElementById('toggleMode').textContent = "Light Mode";
    } else {
        document.body.classList.add("light-mode");
        document.body.classList.remove("dark-mode");
        document.getElementById('toggleMode').textContent = "Dark Mode";
    }
}

document.addEventListener("DOMContentLoaded", function() {
    const savedMode = getModeCookie();
    if (savedMode) {
        applyMode(savedMode);
    } else {
        applyMode("light");
    }

    document.getElementById('toggleMode').addEventListener('click', function() {
        const currentMode = document.body.classList.contains("dark-mode") ? "light" : "dark";
        applyMode(currentMode);
        setModeCookie(currentMode);
    });
});