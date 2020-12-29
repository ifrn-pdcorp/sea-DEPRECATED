import { http } from './config'

export default {
    saveImage: (image) => {
        const fd = new FormData()
        fd.append('image', image)
        fd.append('type_image', 'THUMB_EVENT')
        return http.post('upload', fd)
    },

    getImage: (url) => {
        return http.get(url, {responseType:'blob'})
    }
}