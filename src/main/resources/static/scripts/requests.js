// ADD TO CART
function addCartForm(productId) {
    var quantity = 1;

    axios.post(`user/cart/add`, null, {
        params: {
            productId,
            quantity
        }
    })
        .then(function () {
            alert("Added To Cart.");
        })
        .catch(function () {
            alert("Error Happened!");
        });
}

// UPDATE ITEM IN CART
function updateCartForm(productId) {

    var quantity = $('#qty' + productId).val();
    if (quantity < 1) {
        alert("Enter a positive value.");
        return false;
    }

    axios.post(`user/cart/update`, null, {
        params: {
            productId,
            quantity
        }
    })
        .then(function () {
            alert("Updated Successfully.");
            location.reload();
        })
        .catch(function () {
            alert("Error Happened!");
        });
}

// DELETE FROM CART
function deleteCartForm(productId) {

    axios.post(`user/cart/delete`, null, {
        params: {
            productId
        }
    })
        .then(function () {
            alert("Product was Deleted.");
            location.reload();
        })
        .catch(function () {
            alert("Error Happened!");
        });
}

// DELETE ALL FROM Cart
function deleteAllCartForm() {

    if (!(confirm('Are you sure you want to buy products?'))) return false;

    axios.post(`user/cart/delete-all`, null, {})
        .then(function () {
            alert("Enjoy with your products!");
            location.reload();
        })
        .catch(function () {
            alert("Error Happened!");
        });
}

// ADD TO WISHLIST
function addWishForm(productId) {

    axios.post(`user/wishlist/add`, null, {
        params: {
            productId
        }
    })
        .then(function () {
            alert("Added To Wishlist.");
        })
        .catch(function () {
            alert("Error Happened!");
        });
}

// DELETE FROM WISHLIST
function deleteWishForm(productId) {

    axios.post(`user/wishlist/delete`, null, {
        params: {
            productId
        }
    })
        .then(function () {
            alert("Product was Deleted.");
            location.reload();
        })
        .catch(function () {
            alert("Error Happened!");
        });
}

// SEND MAIL
function sendMessage() {

    axios.post(`/send-feedback`, null, {
        params: {
            name: $('#name').val(),
            email: $('#email').val(),
            subject: $('#subject').val(),
            message: $('#message').val()
        }
    })
        .then(function () {
            $("#sendmessage").css({"display": "block"});
        })
        .catch(function () {
            $("#errormessage").css({"display": "block"});
        });
}


