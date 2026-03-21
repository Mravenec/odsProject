import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider, useAuth } from './hooks/useAuth.jsx';
import LoginPage from './pages/LoginPage/LoginPage.jsx';
import DashboardPage from './pages/DashboardPage/DashboardPage.jsx';
import ProjectCreationPage from './pages/ProjectCreationPage/ProjectCreationPage.jsx';
import ProjectResultsPage from './pages/ProjectResultsPage/ProjectResultsPage.jsx';
import AdminProjectOverviewPage from './pages/Admin/Overview/OverviewPage.jsx';
import AdminResultsReviewPage from './pages/Admin/Results/ResultsPage.jsx';

function App() {
  return (
    <AuthProvider>
      <Router>
        <AppContent />
      </Router>
    </AuthProvider>
  );
}

function AppContent() {
  const { isAuthenticated, user } = useAuth();
  
  if (!isAuthenticated) {
    return <LoginPage />;
  }

  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Navigate to="/dashboard" replace />} />
        <Route path="/dashboard" element={<DashboardPage />} />
        <Route path="/projects/create" element={<ProjectCreationPage />} />
        <Route path="/projects/:projectId/results" element={<ProjectResultsPage />} />
        
        {user?.role === 'admin' && (
          <>
            <Route path="/admin/projects" element={<AdminProjectOverviewPage />} />
            <Route path="/admin/results/:projectId" element={<AdminResultsReviewPage />} />
          </>
        )}
        
        <Route path="*" element={<Navigate to="/dashboard" replace />} />
      </Routes>
    </div>
  );
}

export default App;
