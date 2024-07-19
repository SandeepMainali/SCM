console.log("Script loaded");

// Initialize theme
let currentTheme = getTheme();

// Apply the current theme on page load
document.addEventListener("DOMContentLoaded", () => {
    changePageTheme(currentTheme);
    setupThemeChangeListener();
});

function changePageTheme(theme) {
    // Set the theme in localStorage
    setTheme(theme);

    // Remove old theme if it exists
    document.querySelector("html").classList.remove("light", "dark");

    // Apply the new theme
    document.querySelector("html").classList.add(theme);

    // Update the button text
    const changeThemeButton = document.querySelector("#theme_change_button");
    changeThemeButton.querySelector("span").textContent = theme === "light" ? "Dark" : "Light";
}

// Set theme in localStorage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

// Get theme from localStorage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light"; // Default to "light" if not set
}

// Set up the theme change button listener
function setupThemeChangeListener() {
    const changeThemeButton = document.querySelector("#theme_change_button");

    changeThemeButton.addEventListener("click", () => {
        let newTheme = currentTheme === "dark" ? "light" : "dark";
        currentTheme = newTheme;
        changePageTheme(newTheme);
    });
}
