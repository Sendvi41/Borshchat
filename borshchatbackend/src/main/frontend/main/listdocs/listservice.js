import axios from 'axios'


const TASK_REST_API_URL = "http://localhost:8080/document/getalldocs";


class ListDocService {

    getDocs(){
        return axios.get(TASK_REST_API_URL);
    }

}

export default new ListDocService()