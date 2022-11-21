import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import {getProjects} from "../api/project";
import {RootState} from "../store";

export const fetchProjects = createAsyncThunk(
    'projects/fetchProjects',
    async () => {
        let response = await getProjects();
        return response.data;
    }
)

interface ProjectSlice {
    id:number;
    name:string;
}

interface ProjectsState {
    entities: ProjectSlice[]
    loading: 'idle' | 'pending' | 'succeeded' | 'failed'
}

const initialState = {
    entities: [],
    loading: 'idle'
} as ProjectsState

export const projectsSlice = createSlice(({
    name: 'projects',
    initialState,
    reducers: {

    },
    extraReducers: (builder) => {
        builder.addCase(fetchProjects.fulfilled, (state, action) => {
            state.entities = [...action.payload]
        })
    }
}))

export default projectsSlice.reducer;

export const selectProjects = (state: RootState) => state.projects;
