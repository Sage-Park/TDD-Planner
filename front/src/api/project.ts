import axios from "axios";

export const getProjects = () => {

    return axios.get(`${process.env.REACT_APP_BACK_BASE_URL}/projects`)
}
