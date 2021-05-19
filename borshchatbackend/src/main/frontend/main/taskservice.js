import axios from 'axios'


const TASK_REST_API_URL = "http://localhost:8080/task/getalltasks";
const TASK_REST_API_URL_ONE_TASK = "http://localhost:8080/task/gettask/";


class TaskService {

    getTasks(){
        return axios.get(TASK_REST_API_URL);
    }

    getOnetTask(id){
        return axios.get(TASK_REST_API_URL_ONE_TASK + id.toString())
    }
}

export default new TaskService()