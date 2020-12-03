import { http } from './config'

export default {
    login: (credentials) => {
        return http.post('login', credentials)
    },
}