
const registerButton = document.querySelector(".register-button");
const loginButton = document.querySelector(".login-button");
const container = document.querySelector(".container");
const togglePasswords = document.querySelectorAll(".toggle-password");

if (registerButton && loginButton && container) {
    registerButton.addEventListener("click", () => {
        container.classList.add("right-panel-active");
    });

    loginButton.addEventListener("click", () => {
        container.classList.remove("right-panel-active");
    });
}

togglePasswords.forEach(togglePassword => {
    togglePassword.addEventListener("click", function () {
        const input = document.querySelector(this.dataset.toggle);
        if (!input) return;

        input.type = input.type === "password" ? "text" : "password";
        this.classList.toggle("fa-eye-slash");
    });
});

const registerForm = document.querySelector(".sign-up-container form");

if (registerForm) {
    registerForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const fullName = document.getElementById("fullName").value.trim();
        const displayName = document.getElementById("displayname").value.trim();
        const email = document.getElementById("emails").value.trim();
        const password = document.getElementById("passwordd").value;
        const confirmPassword = document.getElementById("conf").value;

        if (password !== confirmPassword) {
            alert("Mật khẩu và xác nhận mật khẩu không khớp!");
            return;
        }

        try {
            const response = await fetch("register", {
                method: "POST",
                credentials: "same-origin", // 🔥 giữ session nếu server cần
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    fullName,
                    displayName,
                    email,
                    password,
                    confirmPassword
                })
            });

            const data = await response.json();

            if (response.ok && data.status === "success") {
                alert("Đăng ký thành công! Vui lòng kiểm tra email.");
                window.location.reload();
            } else {
                alert(data.message || "Đăng ký thất bại");
            }
        } catch (err) {
            console.error("REGISTER ERROR:", err);
            alert("Lỗi hệ thống, vui lòng thử lại.");
        }
    });
}

const loginForm = document.querySelector(".sign-in-container form");

if (loginForm) {
    loginForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const email = document.getElementById("email").value.trim();
        const password = document.getElementById("password").value;

        try {
            const response = await fetch("login", {
                method: "POST",
                credentials: "same-origin", // 🔥 CỰC KỲ QUAN TRỌNG
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email, password })
            });

            const data = await response.json();
            console.log("LOGIN RESPONSE:", data);

            if (response.ok && data.status === "success") {

                if (data.data && data.data.role === "ADMIN") {
                    window.location.href = "admin/dashboard";
                } else {
                    window.location.href = "home";
                }
            } else {
                alert(data.message || "Email hoặc mật khẩu không đúng");
            }
        } catch (err) {
            console.error("LOGIN ERROR:", err);
            alert("Không thể đăng nhập, vui lòng thử lại.");
        }
    });
}

function validatePassword(password) {
    const regex = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    return regex.test(password);
}

const passwordInput = document.getElementById("passwordd");
const confirmInput = document.getElementById("conf");

if (passwordInput) {
    passwordInput.addEventListener("input", () => {
        document.getElementById("password-error").style.display =
            validatePassword(passwordInput.value) ? "none" : "block";
    });
}

if (confirmInput) {
    confirmInput.addEventListener("input", () => {
        document.getElementById("conf-error").style.display =
            confirmInput.value && confirmInput.value !== passwordInput.value
                ? "block"
                : "none";
    });
}

//------------------------------------------------------------------------//
// const registerButton = document.querySelector(".register-button");
// const loginButton = document.querySelector(".login-button");
// const container = document.querySelector(".container");
// const togglePasswords = document.querySelectorAll(".toggle-password");
//
// // Chuyển đổi giao diện giữa đăng ký và đăng nhập
// registerButton.addEventListener("click", () => {
//     container.classList.add("right-panel-active");
// });
// loginButton.addEventListener("click", () => {
//     container.classList.remove("right-panel-active");
// });
//
// // Hiển thị/Ẩn mật khẩu
// togglePasswords.forEach((togglePassword) => {togglePasswords
//     togglePassword.addEventListener("click", function () {
//         const passwordInput = document.querySelector(
//             this.getAttribute("data-toggle")
//         );
//
//         const type =
//             passwordInput.getAttribute("type") === "password" ? "text" : "password";
//         passwordInput.setAttribute("type", type);
//
//         this.classList.toggle("fa-eye-slash");
//     });
// });
//
// document.querySelector(".sign-up-container form").addEventListener("submit", async (e) => {
//     e.preventDefault(); // Ngăn gửi form truyền thống
//
//     const fullName = document.getElementById("fullName").value;
//     const displayName = document.getElementById("displayname").value;
//     const email = document.getElementById("emails").value;
//     const password = document.getElementById("passwordd").value;
//     const confirmPassword = document.getElementById("conf").value;
//
//     if (password !== confirmPassword) {
//         alert("Mật khẩu và xác nhận mật khẩu không khớp!");
//         return;
//     }
//
//     try {
//         const response = await fetch("register", {
//             method: "POST",
//             credentials: "same-origin",
//             headers: {
//                 "Content-Type": "application/json",
//             },
//             body: JSON.stringify({
//                 fullName,
//                 displayName,
//                 email,
//                 password,
//                 confirmPassword
//             }),
//         });
//
//         if (response.ok) {
//             const data = await response.json();
//             alert("Đăng ký thành công! Vui lòng vào mail để xác nhận.");
//             window.location.reload();
//             // document.querySelector(".sign-in-container form").reset(); // Reset form đăng nhập
//         } else {
//             const errorData = await response.json();
//             alert("Lỗi đăng ký: " + errorData.message);
//         }
//     } catch (error) {
//         console.error("Lỗi khi đăng ký:", error);
//         alert("Đã xảy ra lỗi! Vui lòng thử lại.");
//     }
// });
//
// document.querySelector(".sign-in-container form").addEventListener("submit", async (e) => {
//     e.preventDefault();
//
//     const email = document.getElementById("email").value;
//     const password = document.getElementById("password").value;
//
//
//
//     try {
//         const response = await fetch("login", {
//             method: "POST",
//             // mới thêm để fix đăng nhập //
//             credentials: "same-origin",
//             headers: {
//                 "Content-Type": "application/json",
//             },
//             body: JSON.stringify({
//                 // credentials: "include",
//                 // email,
//                 // password,
//                 email: email,
//                 password: password
//             }),
//         });
//
//         if (response.ok) {
//             const data = await response.json();
//             console.log("Dữ liệu trả về từ server:", data);
//
//             if (data && data.data) {
//                 console.log("Session ID:", data.data.sessionId);
//                 console.log("User ID:", data.data.id);
//                 console.log("Role:", data.data.role);
//
//                 // Lưu vào sessionStorage
//                 sessionStorage.setItem("sessionId", data.data.sessionId);
//                 sessionStorage.setItem("userId", data.data.id);
//                 sessionStorage.setItem("role", data.data.role);
//
//
//
//                 if (data.data.role === "ADMIN") {
//                     window.location.href = "admin/dashboard";
//                 }
//                 else {
//                     window.location.href = "home";
//                 }
//             } else {
//                 alert("Email hoặc mật khẩu không chính xác!");
//             }
//         } else {
//             const errorData = await response.json();
//             alert("Lỗi đăng nhập: " + errorData.message);
//         }
//     } catch (error) {
//         console.error("Lỗi khi đăng nhập:", error);
//         alert("Đã xảy ra lỗi! Vui lòng thử lại.");
//     }
// });
//
//
//
//
// // Hàm kiểm tra mật khẩu
// function validatePassword(password) {
//     const passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
//     return passwordRegex.test(password);
// }
//
// // Hàm kiểm tra khi người dùng nhập mật khẩu
// document.getElementById('passwordd').addEventListener('input', function () {
//     const password = document.getElementById('passwordd').value;
//     if (!validatePassword(password)) {
//         document.getElementById('password-error').style.display = 'block';
//     } else {
//         document.getElementById('password-error').style.display = 'none';
//     }
// });
//
// document.getElementById('conf').addEventListener('input', function () {
//     const password = document.getElementById('passwordd').value;
//     const confirmPassword = document.getElementById('conf').value;
//     if (confirmPassword && confirmPassword !== password) {
//         document.getElementById('conf-error').style.display = 'block';
//     } else {
//         document.getElementById('conf-error').style.display = 'none';
//     }
// });
//
// // Hàm kiểm tra khi người dùng cố gắng đăng ký
// function validateForm() {
//     const password = document.getElementById('passwordd').value;
//     const confirmPassword = document.getElementById('conf').value;
//
//     // Kiểm tra mật khẩu hợp lệ
//     if (!validatePassword(password)) {
//         document.getElementById('password-error').style.display = 'block';
//         return false;
//     }
//     // Kiểm tra mật khẩu và xác nhận mật khẩu có khớp không
//     if (password !== confirmPassword) {
//         document.getElementById('conf-error').style.display = 'block';
//         return false;
//     }
//
//     return true;
// }
//
// // Thêm sự kiện submit để kiểm tra form khi người dùng nhấn nút đăng ký
// document.querySelector('form').addEventListener('submit', function(event) {
//     if (!validateForm()) {
//         event.preventDefault(); // Ngừng gửi form nếu không hợp lệ
//     }
// });
//
//
