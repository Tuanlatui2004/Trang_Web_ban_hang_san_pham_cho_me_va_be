let currentImageIndex = 0;

function changeMainImage(src, element) {
    const mainImg = document.getElementById('mainImage');
    mainImg.style.opacity = '0'; // Hiệu ứng mờ dần

    setTimeout(() => {
        mainImg.src = src;
        mainImg.style.opacity = '1';
    }, 200);

    document.querySelectorAll('.thumbnail').forEach(thumb => {
        thumb.classList.remove('active');
    });
    element.classList.add('active');
}

function nextImage() {
    const thumbs = document.querySelectorAll('.thumbnail');
    currentImageIndex = (currentImageIndex + 1) % thumbs.size;
    thumbs[currentImageIndex].click();
}

function prevImage() {
    const thumbs = document.querySelectorAll('.thumbnail');
    currentImageIndex = (currentImageIndex - 1 + thumbs.length) % thumbs.length;
    thumbs[currentImageIndex].click();
}