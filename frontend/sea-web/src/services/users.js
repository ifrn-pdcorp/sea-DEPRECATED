import {http} from './config'

export default {
    save:(user) => {
        return http.post('users', user)
    },
}