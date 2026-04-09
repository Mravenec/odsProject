import React from 'react'
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom'
import Dashboard from './pages/Dashboard'
import Login from './pages/Login'
import ProjectDetail from './pages/ProjectDetail'
import Evaluation from './pages/Evaluation'
import Configuracion from './pages/Configuracion'

function App() {
  // Simple auth simulation (can be expanded)
  const isAuthenticated = true; // Set to true for development

  return (
    <Router>
      <Routes>
        <Route path="/Login" element={<Login />} />
        <Route path="/login" element={<Navigate to="/Login" replace />} />
        <Route 
          path="/" 
          element={<Navigate to="/Proyectos" replace />} 
        />
        <Route 
          path="/Proyectos" 
          element={isAuthenticated ? <Dashboard /> : <Navigate to="/Login" />} 
        />
        <Route path="/Proyectos/Detalleproyecto/:id" element={<ProjectDetail />} />
        <Route path="/detalle/:id" element={<Navigate to="/Proyectos/Detalleproyecto/:id" replace />} />
        <Route path="/EvaluacionProyecto" element={<Evaluation />} />
        <Route path="/EvaluacionProyecto/:id" element={<Evaluation />} />
        <Route path="/evaluar/:id" element={<Navigate to="/EvaluacionProyecto/:id" replace />} />
        <Route path="/Configuracion" element={<Configuracion />} />
      </Routes>
    </Router>
  )
}

export default App
