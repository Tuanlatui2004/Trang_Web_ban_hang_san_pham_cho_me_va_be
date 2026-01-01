document.addEventListener("DOMContentLoaded", () => {
    const menuItems = document.querySelectorAll(".navbar ul li");
    const userLoginIcon = document.querySelector(".user-login");
    const userPopup = document.querySelector(".user-popup");
    const iframe = document.querySelector("#body iframe");



    // Kiểm tra trạng thái đăng nhập
    const isLoggedIn = localStorage.getItem("isLoggedIn");




    // Hiệu ứng hover cho menu
    menuItems.forEach((item) => {
        let timeout;

        item.addEventListener("mouseenter", () => {
            clearTimeout(timeout);
            menuItems.forEach((i) => i.classList.remove("active"));
            item.classList.add("active");
        });

        item.addEventListener("mouseleave", () => {
            timeout = setTimeout(() => {
                item.classList.remove("active");
            }, 200);
        });

        const submenu = item.querySelector(".submenu");
        if (submenu) {
            submenu.addEventListener("mouseenter", () => {
                clearTimeout(timeout);
            });

            submenu.addEventListener("mouseleave", () => {
                timeout = setTimeout(() => {
                    item.classList.remove("active");
                }, 200);
            });
        }
    });

    // Xử lý sự kiện chuyển đổi iframe
    document.getElementById("logo").addEventListener("click", (event) => {
        event.preventDefault();
        iframe.src = "D:/Trang_Web_ban_hang_san_pham_cho_me_va_be/src/main/webapp/fontend/component/home/homeBody.html";
        history.pushState({ page: "home" }, "Trang chủ", "/home");
    });

    // document.getElementById("cart-link").addEventListener("click", (event) => {
    //     event.preventDefault();
    //     iframe.src = "/web-programming/frontEnd/src/component/cart/Cart.html";
    //     history.pushState({ page: "home" }, "Trang chủ", "/home");
    // });
// Lấy tất cả các mục submenu
    const submenuLinks = document.querySelectorAll(".submenu a");

    submenuLinks.forEach((link) => {
        link.addEventListener("click", (event) => {
            event.preventDefault();

            const pageSrc = link.getAttribute("data-src");
            if (pageSrc) {
                iframe.src = pageSrc;
                history.pushState(
                    { page: pageSrc },
                    link.textContent.trim(),
                    pageSrc
                );
            } else {
                console.error("Không tìm thấy `data-src` cho mục submenu!");
            }
        });
    });

    window.addEventListener("popstate", (event) => {
        if (event.state && event.state.page) {
            iframe.src = event.state.page;
        }
    });



    userLoginIcon.addEventListener("mouseenter", () => {
        userPopup.style.display = "block";
    });

    userLoginIcon.addEventListener("mouseleave", () => {
        setTimeout(() => {
            userPopup.style.display = "none";
        }, 200);
    });

    userPopup.addEventListener("mouseenter", () => {
        userPopup.style.display = "block";
    });

    userPopup.addEventListener("mouseleave", () => {
        userPopup.style.display = "none";
    });

    // Xử lý nút tìm kiếm
    // document.getElementById("search-icon").addEventListener("click", () => {
    //     document.getElementById("search-overlay").style.display = "flex";
    // });
    //
    // document.getElementById("close-search-overlay").addEventListener("click", () => {
    //     document.getElementById("search-overlay").style.display = "none";
    // });
    const sampleProducts = [
        { id: 1, name: "Sữa bột DHA cho bé", price: "450.000đ", image: "../../resource/image/milk.jpg", link: "../../component/product_component/Search_milk.html" },
        { id: 2, name: "Xe tập đi cho bé", price: "850.000đ", image: "../../resource/image/listProduct/product1.jpg", link: "../../component/product_component/Search_Dochoi.html" },
        { id: 3, name: "Quần áo sơ sinh", price: "250.000đ", image: "../../resource/image/listProduct/product2.png", link: "../../component/product_component/Search_Clothes.html" },
        { id: 4, name: "Bỉm tã sơ sinh", price: "320.000đ", image: "../../resource/image/listProduct/product3.jpg", link: "../../component/product_component/Search_bimta.html" },
        { id: 5, name: "Máy pha sữa", price: "1.200.000đ", image: "../../resource/image/listProduct/product4.jpg", link: "../../component/product_component/Search_mayphasua.html" },
        { id: 6, name: "Vitamin cho mẹ bầu", price: "380.000đ", image: "../../resource/image/listProduct/product5.jpg", link: "../../component/product_component/Search_thuocvitamin.html" },
        { id: 7, name: "Bột ăn dặm", price: "150.000đ", image: "../../resource/image/listProduct/product6.jpg", link: "../../component/product_component/Search_eat.html" },
        { id: 8, name: "Quần áo thời trang bé", price: "280.000đ", image: "../../resource/image/listProduct/product7.png", link: "../../component/product_component/Search_ThoiTrang.html" }
    ];

    const searchInput = document.getElementById('search-input');
    const searchResults = document.getElementById('search-results');
    const resultsList = searchResults.querySelector('.search-results-list');
    const searchLoading = searchResults.querySelector('.search-loading');
    let searchTimeout;

    function searchProducts(query) {
        if (!query || query.trim().length === 0) {
            searchResults.style.display = 'none';
            return;
        }

        searchLoading.style.display = 'block';
        resultsList.innerHTML = '';

        setTimeout(() => {
            const filteredProducts = sampleProducts.filter(product =>
                product.name.toLowerCase().includes(query.toLowerCase())
            );

            searchLoading.style.display = 'none';

            if (filteredProducts.length === 0) {
                resultsList.innerHTML = '<div class="no-results">Không tìm thấy sản phẩm nào</div>';
            } else {
                resultsList.innerHTML = filteredProducts.map(product => `
                        <a href="${product.link}" class="search-result-item">
                            <img src="${product.image}" alt="${product.name}" onerror="this.src='../../resource/image/listProduct/product1.jpg'">
                            <div class="search-result-info">
                                <div class="search-result-name">${product.name}</div>
                                <div class="search-result-price">${product.price}</div>
                            </div>
                        </a>
                    `).join('');
            }

            searchResults.style.display = 'block';
        }, 300);
    }

    searchInput.addEventListener('input', (e) => {
        clearTimeout(searchTimeout);
        const query = e.target.value;
        searchTimeout = setTimeout(() => {
            searchProducts(query);
        }, 300);
    });

    searchInput.addEventListener('focus', () => {
        if (searchInput.value.trim().length > 0) {
            searchProducts(searchInput.value);
        }
    });

    document.addEventListener('click', (e) => {
        if (!e.target.closest('.search-wrapper')) {
            searchResults.style.display = 'none';
        }
    });

    searchInput.addEventListener('keydown', (e) => {
        if (e.key === 'Enter') {
            const query = searchInput.value.trim();
            if (query.length > 0) {
                window.location.href = `../../component/home/search.html?q=${encodeURIComponent(query)}`;
            }
        }
    });

    // Xử lý nút "Trang của tôi"
    document.getElementById("my-page-link").addEventListener("click", (event) => {
        event.preventDefault();
        if (!isLoggedIn) {
            alert("Bạn cần đăng nhập trước!");
        } else {
            iframe.src = "D:/Trang_Web_ban_hang_san_pham_cho_me_va_be/src/main/webapp/fontend/src/pages/auth.html";
            history.pushState({ page: "user-profile" }, "Trang của tôi", "/user-profile");
        }
    });

    // Xử lý nút "Đơn hàng"
    document.getElementById("cart-link").addEventListener("click", (event) => {
        event.preventDefault();
        if (!isLoggedIn) {
            alert("Bạn cần đăng nhập trước!");
        } else {
            iframe.src = "/web-programming/frontEnd/src/component/cart/Cart.html";
            history.pushState({ page: "user-orders" }, "Giỏ Hàng", "/cart");
        }
    });

    window.addEventListener("message", (event ) => {
        if (event.data.type === "navigate") {
            iframe.src = event.data.url;
            history.pushState({page:"checkout"}, "Thanh toan", "/cart/payment");
        }
    })



    // Xử lý trạng thái đăng nhập
    const loginLink = document.getElementById("login-link");
    if (isLoggedIn) {
        loginLink.textContent = "Đăng xuất";
        loginLink.addEventListener("click", (event) => {
            event.preventDefault();
            localStorage.removeItem("isLoggedIn");
            localStorage.removeItem("userEmail");
            loginLink.textContent = "Đăng nhập/Đăng ký";
            window.location.reload();
            alert("Bạn đã đăng xuất!");
            window.location.href = "/web-programming/frontEnd/src/pages/auth.html";

        });
    } else {
        loginLink.textContent = "Đăng nhập/Đăng ký";
        loginLink.addEventListener("click", (event) => {
            event.preventDefault();
            window.location.href = "/web-programming/frontEnd/src/pages/auth.html";
        });
    }
});
