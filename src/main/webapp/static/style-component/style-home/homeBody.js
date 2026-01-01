const images = document.querySelectorAll(".image-container img");
const textOverlay = document.querySelector(".text-overlay");
const productDescription = document.getElementById("product-description");
const navbar = document.querySelector(".navbar");

function showImage(image_id) {
    images.forEach((img) => {
        img.classList.remove("active");
        if (img.id === image_id) {
            img.classList.add("active");
        }
    });

// xem lại cai imageId coi nó thuộc cái nào
    if (image_id === "SP3" || image_id === "SP4") {
        textOverlay.style.color = "white";
        navbar.classList.remove("light");
        navbar.classList.add("dark");
    } else {
        textOverlay.style.color = "black";
        navbar.classList.remove("dark");
        navbar.classList.add("light");
    }


    const activeLink = document.querySelector(`.navbar a[onclick="showImage('${image_id}')"]`);
    if (activeLink) {
        productDescription.textContent = activeLink.getAttribute("data-text");
    }
}


document.addEventListener('DOMContentLoaded', function() {
    const navLinks = document.querySelectorAll('.nav-link');
    const productShowcases = document.querySelectorAll('.product-showcase');

    navLinks.forEach(link => {
        link.addEventListener('click', function() {
            // Remove active class from all links
            navLinks.forEach(l => l.classList.remove('active'));

            // Add active class to current link
            this.classList.add('active');

            // Hide all product showcases
            productShowcases.forEach(showcase => {
                showcase.classList.remove('active');
            });

            // Show current product showcase
            const targetId = this.getAttribute('data-target');
            document.getElementById(targetId).classList.add('active');
        });
    });
});

