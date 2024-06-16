document.getElementById('form').addEventListener('submit', function (event) {
    event.preventDefault();


    const nameUpdate = document.getElementById("name");

    const Userdata = {
        name: nameUpdate.value.trim(),
    };

    axios.put(`/api/users/update-profile`, Userdata)
        .then(response => {
            toastr.success("Cập nhật thành công!");


            nameUpdate.value = response.data.name;
        })
        .catch(error => {
            console.error(error);
            toastr.error("Cập nhật không thành công!");
        });

});

document.getElementById('modalUpdatePassword').addEventListener('submit', function (event) {
    event.preventDefault();


    const oldPassword = document.getElementById("oldPassword");
    const newPassword = document.getElementById("newPassword");
    const newPasswordAgain = document.getElementById("newPasswordAgain");

    const Userdata = {
        oldpassword: oldPassword.value.trim(),
        newPassword : newPassword.value.trim(),
        newPasswordAgain : newPasswordAgain.value.trim(),
    };

    axios.put(`/api/users/update-Password`, Userdata)
        .then(response => {
            toastr.success("Cập nhật thành công!");


            newPasswordAgain.value = response.data.name;
        })
        .catch(error => {
            console.error(error);
            toastr.error("Cập nhật không thành công!");
        });

});



$('modalUpdatePassword').validate({
    rules: {
        password: {
            required: true,
            minlength: 6,
        },
        confirmPassword: {
            required: true,
            equalTo: "#password"
        }
    },
    messages: {
        password: {
            required: "Mật khẩu không được để trống",
            minlength: "Mật khẩu phải có ít nhất 6 ký tự",
        },
        confirmPassword: {
            required: "Mật khẩu không được để trống",
            equalTo: "Mật khẩu không khớp"
        },
    },
    errorElement: 'span',
    errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-group').append(error);
    },
    highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
    },
    unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
    }

});


$('#form').validate({
    rules: {
        name: {
            required: true
        },
        email: {
            required: true,
            email: true,
        },
    },
    messages: {
        name: {
            required: "Tên user không được để trống"
        },
        email: {
            required: "Email không được để trống",
            email: "Email không đúng định dạng"
        }

    },
    errorElement: 'span',
    errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-group').append(error);
    },
    highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
    },
    unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
    }
});
