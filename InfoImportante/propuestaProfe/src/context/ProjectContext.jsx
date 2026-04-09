import React, { createContext, useContext, useState, useEffect } from 'react';

const ProjectContext = createContext();

const STORAGE_KEY = 'utn_project_hub_data';

const DEFAULT_DATA = {
  projects: [
    {
      id: 1,
      title: "Alfabetización en la zona de Tuis de Turrialba",
      description: "Llevar a cabo una serie de capacitaciones a las personas de la zona con la finalidad de fomentar el emprendurismo",
      objective: "Llevar a cabo una serie de capacitaciones a las personas de la zona con la finalidad de fomentar el emprendurismo",
      location_city: "La Suiza",
      location_canton: "Turrialba",
      location_province: "Cartago",
      location_district: "La Suiza",
      start_date: "2026-04-02",
      end_date: "2026-05-31",
      ods: [
        { id: 8, name: "Trabajo Decente y Crecimiento Económico", color: '#A21942' },
        { id: 5, name: "Igualdad de Género", color: '#FF3A21' }
      ]
    }
  ],
  areas: [
    { id: 1, name: "AEAS Sede Atenas" },
    { id: 2, name: "AEAS Sede Central" },
    { id: 3, name: "AEAS Sede Guanacaste" },
    { id: 4, name: "AEAS Sede San Carlos" },
    { id: 5, name: "AEAS Sede Puntarenas" }
  ],
  personnel: [
    { id: 1, name: 'Ileana Cartín Guerrero', areaId: 1 },
    { id: 2, name: 'Marco Tulio López Durán', areaId: 1 },
    { id: 3, name: 'Juan Perez', areaId: 2 },
    { id: 4, name: 'Maria Rodriguez', areaId: 3 },
    { id: 5, name: 'Luis Gonzalez', areaId: 4 },
    { id: 6, name: 'Ana Morales', areaId: 5 }
  ]
};

export const ProjectProvider = ({ children }) => {
  const [data, setData] = useState(() => {
    const saved = localStorage.getItem(STORAGE_KEY);
    return saved ? JSON.parse(saved) : DEFAULT_DATA;
  });

  useEffect(() => {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(data));
  }, [data]);

  const addProject = (project) => {
    setData(prev => ({
      ...prev,
      projects: [...prev.projects, { ...project, id: Date.now() }]
    }));
  };

  const deleteProject = (id) => {
    setData(prev => ({
      ...prev,
      projects: prev.projects.filter(p => p.id !== id)
    }));
  };

  const addArea = (name) => {
    setData(prev => ({
      ...prev,
      areas: [...prev.areas, { id: Date.now(), name }]
    }));
  };

  const deleteArea = (id) => {
    setData(prev => ({
      ...prev,
      areas: prev.areas.filter(a => a.id !== id),
      personnel: prev.personnel.map(p => p.areaId === id ? { ...p, areaId: null } : p)
    }));
  };

  const addPersonnel = (name, areaId) => {
    setData(prev => ({
      ...prev,
      personnel: [...prev.personnel, { id: Date.now(), name, areaId: parseInt(areaId) || null }]
    }));
  };

  const deletePersonnel = (id) => {
    setData(prev => ({
      ...prev,
      personnel: prev.personnel.filter(p => p.id !== id)
    }));
  };

  return (
    <ProjectContext.Provider value={{
      ...data,
      addProject,
      deleteProject,
      addArea,
      deleteArea,
      addPersonnel,
      deletePersonnel
    }}>
      {children}
    </ProjectContext.Provider>
  );
};

export const useProjects = () => {
  const context = useContext(ProjectContext);
  if (!context) {
    throw new Error('useProjects must be used within a ProjectProvider');
  }
  return context;
};
