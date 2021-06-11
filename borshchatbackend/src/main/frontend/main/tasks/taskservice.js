import axios from 'axios'


const TASK_REST_API_URL = "http://localhost:8080/task/getalltasks";
const TASK_REST_API_URL_ONE_TASK = "http://localhost:8080/task/gettask/";
const TASK_REST_API_URL_ADD_COMMENT = "http://localhost:8080/taskcomment/addcomment";
const TASK_REST_API_URL_COMMENTS = "http://localhost:8080/taskcomment/getcomments/";
const TASK_REST_API_URL_UPDATE = "http://localhost:8080/task/updatetask";

class TaskService {

    getTasks(){
        return axios.get(TASK_REST_API_URL);
    }

    getComments(taskid){
        return axios.get(TASK_REST_API_URL_COMMENTS + taskid.toString());
    }

    getOnetTask(id){
        return axios.get(TASK_REST_API_URL_ONE_TASK + id.toString())
    }

    addOneComment(comment, consult_id, date, task_id){
        let data =  {
            id: '',
            comment: comment,
            consultant_id: consult_id,
            date: date,
            task_id: task_id
        };
        return axios.post(TASK_REST_API_URL_ADD_COMMENT,data);
    }

    updateTask(task){
        return axios.post(TASK_REST_API_URL_UPDATE,task);
    }




}

export default new TaskService()