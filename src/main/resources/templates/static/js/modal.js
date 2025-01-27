// modal.js

document.addEventListener('DOMContentLoaded', function() {
    const modal = document.getElementById("pizzaModal");
    const closeModal = document.querySelector(".close-modal");
    const openModalBtns = document.querySelectorAll(".open-modal-btn");

    openModalBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            const pizzaId = btn.getAttribute("data-id");
            // Делаем fetch к /pizzas/{id}, получаем JSON
            fetch(`/pizzas/${pizzaId}`)
                .then(response => response.json())
                .then(data => {
                    // Заполняем данные в модалке
                    document.getElementById("modalPizzaName").textContent = data.name;
                    document.getElementById("modalPizzaDescription").textContent = data.description;
                    document.getElementById("modalPizzaPrice").textContent = data.price;
                    document.getElementById("modalPizzaImage").src = data.imageUrl;
                    document.getElementById("modalPizzaId").value = data.id;

                    // Показываем модалку
                    modal.style.display = "block";
                })
                .catch(error => console.log(error));
        });
    });

    // Клик по значку "закрыть"
    closeModal.addEventListener('click', () => {
        modal.style.display = "none";
    });

    // Закрытие при клике вне модального окна
    window.addEventListener('click', (event) => {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });
});
