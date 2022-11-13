import {useEffect, useState} from "react";
import {createProject, getProjects} from "./api/project";
import {Button} from "@mui/material";
import ProjectFormDialog from "./components/ProjectFormDialog";

type Project = {
  id: number;
  name:string;
}
const Home = () => {

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
        {projects.map((project) => (
            <div>{project.name}</div>
        ))}
        {projects.length === 0 && (<div>프로젝트가 없습니다.</div>)}
        <div>
          <Button variant="contained" onClick={openModal}>프로젝트 추가</Button>
        </div>
        <ProjectFormDialog open={modalOpen} closeDialog={closeModal} submit={submitDialog}/>
      </>
  );
};

export default Home;
