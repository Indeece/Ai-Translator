const api = "http://localhost:8080";
let token = null;

function showContainer(containerId) {
    document.getElementById('authContainer').style.display = 'none';
    document.getElementById('registerContainer').style.display = 'none';
    document.getElementById('translatorContainer').style.display = 'none';

    document.getElementById(containerId).style.display = 'block';
}

function navigate(path) {
    if (window.location.pathname !== path) {
        window.history.pushState({}, '', path);
        route();
    }
}

function route() {
    const path = window.location.pathname;

    if (token) {
        // Авторизованы — показываем переводчик
        showContainer('translatorContainer');
        if (path !== '/translate') {
            window.history.replaceState({}, '', '/translate');
        }
        return;
    }

    if (path === '/' || path === '/login') {
        showContainer('authContainer');
    } else if (path === '/register') {
        showContainer('registerContainer');
    } else {
        // Для неизвестных путей — редирект на логин
        navigate('/login');
    }
}

window.onpopstate = () => route();

document.addEventListener('DOMContentLoaded', () => {
    route();
});

function register() {
    const username = document.getElementById("regUsername").value.trim();
    const email = document.getElementById("regEmail").value.trim();
    const password = document.getElementById("regPassword").value.trim();

    if (!username || !email || !password) {
        alert("Please fill all fields.");
        return;
    }

    fetch(`${api}/auth/registration`, {
        method: "POST",
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, email, password })
    })
        .then(async res => {
            if (!res.ok) {
                const errorText = await res.text(); // Читаем текст ошибки
                alert(errorText || "Registration failed");
                return;
            }
            const successText = await res.text(); // Читаем текст успешного ответа ("User added")
            alert(successText); // Показываем сообщение от сервера
            navigate('/login'); // Перенаправляем на логин
        })
        .catch(err => {
            console.error(err);
            alert("Network error. Check console.");
        });
}

function signIn() {
    const email = document.getElementById("loginEmail").value.trim();
    const password = document.getElementById("loginPassword").value.trim();

    if (!email || !password) {
        alert("Please fill all fields.");
        return;
    }

    fetch(`${api}/auth/signIn`, {
        method: "POST",
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password })
    })
        .then(res => {
            if (!res.ok) throw new Error('Login failed');
            return res.json();
        })
        .then(data => {
            token = data.token;
            navigate('/translate');
        })
        .catch(err => {
            alert("Login failed");
            console.error(err);
        });
}

function logout() {
    token = null;
    navigate('/login');
}

function toggleAuth() {
    const authContainer = document.getElementById('authContainer');
    const registerContainer = document.getElementById('registerContainer');

    if (authContainer.style.display === 'none') {
        authContainer.style.display = 'block';
        registerContainer.style.display = 'none';
        navigate('/login');
    } else {
        authContainer.style.display = 'none';
        registerContainer.style.display = 'block';
        navigate('/register');
    }
}

function translateText() {
    const text = document.getElementById("inputText").value.trim();
    const fromLang = document.getElementById("fromLang").value || null;
    const toLang = document.getElementById("toLang").value || null;

    if (!text) {
        document.getElementById("outputText").value = '';
        return;
    }

    fetch(`${api}/ai/translate`, {
        method: "POST",
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ message: text, fromLang, toLang })
    })
        .then(res => {
            if (!res.ok) throw new Error("Translation failed");
            return res.text();
        })
        .then(translated => {
            // Убираем кавычки, если сервер вернул JSON-строку
            if (translated.startsWith('"') && translated.endsWith('"')) {
                translated = translated.slice(1, -1)
                    .replace(/\\"/g, '"')
                    .replace(/\\\\/g, '\\');
            }
            document.getElementById("outputText").value = translated;
        })
        .catch(err => {
            document.getElementById("outputText").value = "Translation failed.";
            console.error("Translation error", err);
        });
}
