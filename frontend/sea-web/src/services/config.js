import axios from 'axios'

export const http = axios.create({
    baseURL: 'http://localhost:8080/'
})

http.interceptors.request.use(config => {
    var token = sessionStorage.getItem('userToken')
    if (token)
        config.headers.common['Authorization'] = 'Bearer ' + token
    return config
})