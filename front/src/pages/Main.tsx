import {useEffect, useState} from "react";
import {createProject, getProjects} from "../api/project";
import {Button} from "@mui/material";
import ProjectFormDialog from "../components/ProjectFormDialog";
import Header from "../components/Header";

type Project = {
  id: number;
  name:string;
}
const Main = () => {

  let [projects, setProjects] = useState<Project[]>([]);
  let [modalOpen, setModalOpen] = useState(false);

  useEffect( () => {

    getProjects().then((response) => {
      setProjects(response.data)
    })

  }, [])

  const openModal = () => {
    setModalOpen(true);
  }

  const closeModal = () => {
    setModalOpen(false)
  }

  const submitDialog = async (name:string) => {
    let response = await createProject(name);

    getProjects().then((response) => {
      setProjects(response.data)
    })

    closeModal();
  }


  return (
      <>
        <Header />
        <div>main</div>
      </>
  );
};

export default Main;
