import axios from 'axios'

export const http = axios.create({
    baseURL: 'http://localhost:8080/'
})

http.interceptors.request.use(config => {
    var token = localStorage.getItem('userToken')
    if (token)
        config.headers.common['Authorization'] = token
    return config
})