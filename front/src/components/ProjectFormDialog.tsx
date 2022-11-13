import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, TextField} from "@mui/material";
import {useState} from "react";

type ProjectFormProp = {
    open: boolean;
    closeDialog: any;
    submit: any;
}

export default function ProjectFormDialog({open, closeDialog, submit}:ProjectFormProp) {

    let [name, setName] = useState('');

    return (
        <Dialog open={open} onClose={closeDialog}>
            <DialogTitle>프로젝트 추가</DialogTitle>
            <DialogContent>
                <DialogContentText>

                </DialogContentText>
                <TextField
                    label="프로젝트 이름"
                    type="text"
                    onChange={(event) => setName(event.target.value)}
                    value={name}
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={closeDialog}>취소</Button>
                <Button onClick={() => {submit(name); setName('')}}>추가</Button>
            </DialogActions>
        </Dialog>
    )


}
