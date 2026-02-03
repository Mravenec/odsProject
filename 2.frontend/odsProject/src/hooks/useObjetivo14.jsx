import { useState, useEffect } from 'react';
import { objetivo14Service } from '../services/objetivo14Service';

export const useObjetivo14 = () => {
  const [data, setData] = useState({});
  const [loading, setLoading] = useState({});
  const [error, setError] = useState({});

  const fetchData = async (indicator) => {
    setLoading(prev => ({ ...prev, [indicator]: true }));
    setError(prev => ({ ...prev, [indicator]: null }));
    
    try {
      const methodName = `getIndicador_${indicator}`;
      const result = await objetivo14Service[methodName]();
      setData(prev => ({ ...prev, [indicator]: result }));
    } catch (err) {
      setError(prev => ({ ...prev, [indicator]: err.message }));
    } finally {
      setLoading(prev => ({ ...prev, [indicator]: false }));
    }
  };

  const fetchAllData = async () => {
    const indicators = [
      '14_1_1', '14_2_1', '14_3_1', '14_4_1', '14_5_1', '14_6_1',
      '14_7_1', '14_a_1', '14_b_1', '14_c_1'
    ];

    await Promise.all(indicators.map(indicator => fetchData(indicator)));
  };

  return {
    data,
    loading,
    error,
    fetchData,
    fetchAllData
  };
};
