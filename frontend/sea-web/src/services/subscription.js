import { http } from './config'

export default {
    save: (subscription) => {
        return http.post('subscriptions', subscription)
    },

    getByEventId: (id) => {
        return http.get('subscriptions/users/events/' + id)
    },

    delete: (id) => {
        return http.delete('subscriptions/' + id)
    }
}