const apiUrl = 'http://localhost:8080/api/v1/pizzas';
const root = document.getElementById("root");

const renderIngredients = (ingredients) => {
    let content = '<ul class="list-unstyled">';
    ingredients.forEach((ing) => {
        content += `<li>${ing.name}</li>`;
    });
    content += '</ul>';
    return content;
}

const renderPizza = (element) => {
    console.log('element', element);
    return `<div class="col-12 col-md-6 col-lg-4 mb-4">
                <div class="card h-100">
                    <div style="overflow: hidden; height: 200px;" class="d-flex justify-content-center align-items-center">
                        <img src="${element.image}" class="card-img-top img-fluid" alt="${element.name}">
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">${element.name}</h5>
                        <h6 class="card-subtitle mb-2 text-body-secondary">${element.description}</h6>
                        <p class="card-text">Price: ${element.price}</p>
                        <div>${renderIngredients(element.ingredients)}</div>
                    </div>
                </div>
            </div>`;
};



const renderPizzaList = (data) => {
    let content;
    if (data.length > 0) {
        content = '<div class="row">';
        data.forEach((element) => {
            content += renderPizza(element);
        });
        content += '</div>';
    } else {
        content = '<div class="alert alert-info">The list is empty</div>';
    }
    root.innerHTML = content;
}

const getPizza = async () => {
    try {
        const response = await axios.get(apiUrl);
        renderPizzaList(response.data);
        console.log('response', response)
    } catch (error) {
        console.log(error);
    } 
};

getPizza();
