import { http } from './config'

export default {
    getAll: () => {
        return http.get('events')
    },

    get: (id) => {
        return http.get('events/' + id)
    },

    save: (event) => {
        return http.post('events', event)
    }
}