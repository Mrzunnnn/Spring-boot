document.getElementById('loginForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    axios.post('/api/auth/login', {
        email: username,
        password: password
    })
        .then(response => {
            toastr.success('Đăng nhập thành công!');
            setTimeout(() => {
                window.location.href = '/';
            }, 1000);
        })
        .catch(error => {
            toastr.error('Đăng nhập thất bại. Vui lòng kiểm tra lại tài khoản hoặc mật khẩu.');
        });
});
