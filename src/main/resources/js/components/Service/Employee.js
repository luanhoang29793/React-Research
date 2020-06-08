const baseUrl = "http://localhost:8080/api/employee"
import Axios from "axios";
const employee = {};

employee.list = async () => {
    const urlList = baseUrl+"/list"
    const  res = await  Axios.get(urlList)
        .then(response => {return response.data})
        .catch((error => {return error}))
    return res;
}

employee.get = async (id) => {
    const urlGet = baseUrl+"/get/"+id
    const res = await Axios.get(urlGet)
        .then(response=> {return response.data })
        .catch(error=>{ return error; })
    return res;
}


employee.create = async (state) => {

    const dataPost = {
        name: state.fieldName,
        email: state.fieldEmail,
        phone: state.fieldPhone,
        address: state.fieldAddress
    }

    const urlPost = baseUrl+"/create"

    const res = await Axios.post(urlPost,dataPost)
        .then(response=>{
            const data = { success: true, message: response.data }
            return data;
        })
        .catch(error=>{
            const data = { success: false, message: error.response.data }
            return data;
        })

    return res;
}

export default employee