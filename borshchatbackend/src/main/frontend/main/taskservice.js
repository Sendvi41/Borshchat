import axios from 'axios'


const TASK_REST_API_URL = "http://localhost:8080/task/getalltasks";


class TaskService {

    getTasks(){
        return axios.get(TASK_REST_API_URL);
    }

}

export default new TaskService()