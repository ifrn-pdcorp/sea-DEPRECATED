import { http } from './config'

export default {
    getAll: () => {
        return http.get('events')
    },

    save: (event) => {
        return http.post('events', event)
    }
}