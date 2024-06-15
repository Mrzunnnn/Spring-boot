const btnFavorite = document.getElementById('btn-favorite');

const addToFavorite = (movieId) => {
    axios.post(`/api/favorites?movieId=${movieId}`)
        .then(response => {
            toastr.success('Đã thêm vào danh sách yêu thích')
            isFavorite = true;
            btnFavorite.innerText = "Xóa khỏi yêu thích"
        })
        .catch(error => {
            console.log(error)
            toastr.error('Đã có lỗi xảy ra')
        })
}

const removeFromFavorite = (movieId) => {
    axios.delete(`/api/favorites?movieId=${movieId}`)
        .then(response => {
            toastr.success('Đã xóa khỏi danh sách yêu thích')
            isFavorite = false;
            btnFavorite.innerText = "Thêm vào yêu thích"
        })
        .catch(error => {
            console.log(error)
            toastr.error('Đã có lỗi xảy ra')
        })
}

btnFavorite.addEventListener('click',function (){
    if (isFavorite){
        removeFromFavorite(movie.id);
    }else {
        addToFavorite(movie.id);
    }
})


document.addEventListener('DOMContentLoaded', function () {
    const favoriteList = document.getElementById('favorite-list');

    console.log(favoriteList);

    favoriteList.addEventListener('click', function (event) {
        if (event.target.classList.contains('fa-circle-xmark') || event.target.classList.contains('delete-favorite')) {
            event.preventDefault();
            event.stopPropagation();
            const movieItem = event.target.closest('.movie-item');
            const favoriteId = movieItem.getAttribute('data-favorite-id');

            removeFromFavorite(favoriteId);
        }
    });
});